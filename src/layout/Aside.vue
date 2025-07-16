<template>
  <a-layout-sider
      :style="{ overflowX: 'hidden', height: '100vh', position: 'fixed', left: 0, backgroundColor: 'white' }"
      width="240">
    <div class="logo">
      <!-- <i class="iconfont icon-r-building" style="font-size: 26px;margin-right: 5px;"></i> -->
      物流管理系统
    </div>
    <a-menu theme="light" mode="inline">
      <a-sub-menu v-for="(item, index) in menus" :key="index">
        <span slot="title">
          <i :class="'iconfont icon-r-' + item.icon" style="font-size: 24px;margin-right: 4px;"></i>
          <span style="font-size: 20px;">{{ item.title }}</span>
        </span>
        <a-menu-item v-for="menu in item.children" :key="menu.title">
          <router-link :to="menu.path"  style="font-size: 18px;">
            <i :class="'iconfont icon-r-' + menu.icon" style="font-size: 22px;margin-right: 4px;"></i>
            {{ menu.title }}
          </router-link>
        </a-menu-item>
      </a-sub-menu>
    </a-menu>
  </a-layout-sider>
</template>

<script>

export default {

  data() {
    return {
      menus: [
        {
          title: '基础管理',
          icon: 'home',
          children: [
            {title: '商品管理', path: '/commodity', icon: 'shield'},
            {title: '员工管理', path: '/employee', icon: 'user2'},
            {title: '仓库管理', path: '/warehouse', icon: 'building'},
          ]
        },
        {
          title: '销售管理',
          icon: 'paper',
          children: [
            {title: '销售开票', path: '/sale/create', icon: 'yes'},
            {title: '销售记录', path: '/sale/record', icon: 'edit'},
          ]
        },
        {
          title: '配送管理',
          icon: 'right',
          children: [
            {title: '申请配送', path: '/delivery/create', icon: 'yes'},
            {title: '配送列表', path: '/delivery/list', icon: 'list'},
          ]
        },
        {
          title: '运输管理',
          icon: 'refresh',
          children: [
            {title: '车辆资料', path: '/vehicle', icon: 'shield'},
            {title: '驾驶员资料', path: '/driver', icon: 'user3'},
          ]
        },

        {
          title: '系统管理',
          icon: 'setting',
          children: [
            {title: '安全设置', path: '/security', icon: 'shield'},
            {title: '操作员管理', path: '/admin', icon: 'user1'},
            {title: '权限列表', path: '/role', icon: 'add'}
          ]
        }
      ]
    }
  },

}

</script>

<style scoped>

#components-layout-demo-fixed-sider .logo {
  padding: 10px 15px;
  height: 50px;
  font-size: 20px;
  margin: 16px;
  color: black;
  letter-spacing: 2px;
}

.ant-menu {
  letter-spacing: 1px;
}

.logo img {
  width: 32px;
  height: 32px;
  margin-right: 5px;
}

</style>
