package com.logistics.service.transport;

import com.logistics.exception.InvalidStatusTransitionException;
import com.logistics.exception.ResourceNotFoundException;
import com.logistics.model.DeliveryTask;
import com.logistics.model.FreightRule;
import com.logistics.model.Orders;
import com.logistics.repository.transport.DeliveryTaskRepository;
import com.logistics.repository.transport.FreightRuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    public DeliveryService(DeliveryTaskRepository deliveryTaskRepository, FreightRuleRepository freightRuleRepository) {
        this.deliveryTaskRepository = deliveryTaskRepository;
        this.freightRuleRepository = freightRuleRepository;
    }

    private final DeliveryTaskRepository deliveryTaskRepository;
    private final FreightRuleRepository freightRuleRepository;

    public double calculateFreight(double distance) {
        // 1. 尝试查找适用规则
        Optional<FreightRule> applicableRule = freightRuleRepository.findApplicableRule(distance);

        if (applicableRule.isPresent()) {
            // 如果找到适用规则，直接使用该规则的单价
            return distance * applicableRule.get().getUnitPrice();
        }

        // 2. 没有找到适用规则，使用分段计算
        List<FreightRule> rules = freightRuleRepository.findAllByOrderByMinDistanceAsc();
        double totalCost = 0;
        double remainingDistance = distance;

        for (FreightRule rule : rules) {
            if (remainingDistance <= 0) break;

            double segmentMaxDistance = rule.getMaxDistance() - rule.getMinDistance();
            double segmentDistance = Math.min(remainingDistance, segmentMaxDistance);

            if (segmentDistance > 0) {
                totalCost += segmentDistance * rule.getUnitPrice();
                remainingDistance -= segmentDistance;
            }
        }

        // 3. 如果还有剩余距离，使用最后一个规则的单价
        if (remainingDistance > 0 && !rules.isEmpty()) {
            FreightRule lastRule = rules.get(rules.size() - 1);
            totalCost += remainingDistance * lastRule.getUnitPrice();
        }

        return totalCost;
    }

    @Transactional
    public DeliveryTask updateDeliveryStatus(Long taskId, DeliveryTask.DeliveryStatus newStatus) {
        DeliveryTask task = deliveryTaskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("运输任务不存在: " + taskId));

        // 验证状态转换是否合法
        if (!isValidStatusTransition(task.getStatus(), newStatus)) {
            throw new InvalidStatusTransitionException(
                    "无效的状态转换: 从 " + task.getStatus() + " 到 " + newStatus);
        }

        // 更新状态
        task.setStatus(newStatus);

        // 如果运输完成，更新订单状态
        if (newStatus == DeliveryTask.DeliveryStatus.COMPLETED) {
            Orders orders = task.getOrders();
            orders.setStatus(Orders.OrderStatus.COMPLETED);
            // 这里应该调用orderRepository.save(orders)，但为了解耦，由调用方处理
        }

        return deliveryTaskRepository.save(task);
    }

    public DeliveryTask getDeliveryTaskById(Long taskId) {
        return deliveryTaskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("运输任务不存在: " + taskId));
    }

    public DeliveryTask getDeliveryTaskByOrderId(Long orderId) {
        return deliveryTaskRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("订单 " + orderId + " 没有运输任务"));
    }

    public List<DeliveryTask> getAllDeliveryTasks() {
        return deliveryTaskRepository.findAll();
    }

    private boolean isValidStatusTransition(DeliveryTask.DeliveryStatus current,
                                            DeliveryTask.DeliveryStatus next) {
        // 状态流转规则：待发货→运输中→已到达→已完成
        switch (current) {
            case PENDING:
                return next == DeliveryTask.DeliveryStatus.SHIPPING;
            case SHIPPING:
                return next == DeliveryTask.DeliveryStatus.ARRIVED;
            case ARRIVED:
                return next == DeliveryTask.DeliveryStatus.COMPLETED;
            default:
                return false;
        }
    }
}