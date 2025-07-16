<template>
  <div>
    <a-button  class="editable-add-btn" @click="addVisible = true;clearInput()">
      添加操作员
    </a-button>
    <a-table :scroll="{x: true}" :columns="columns" :data-source="admins" rowKey="id" :loading="loading">
      <a slot="email" slot-scope="email">{{ email }}</a>
      <span slot="customTitle"><a-icon type="smile-o"/> 邮箱</span>
      <span slot="tags" slot-scope="tags">
      <a-tag
          v-for="tag in tags"
          :key="tag"
          :color="tag === 'loser' ? 'volcano' : tag.length > 5 ? 'geekblue' : 'green'"
      >
        {{ tag.toUpperCase() }}
      </a-tag>
    </span>
      <span slot="action" slot-scope="text, record, index">
      <a-dropdown>
        <a class="ant-dropdown-link" @click="e => e.preventDefault()" style="font-size: 20px;">操作
          <i class="iconfont icon-r-bottom" style="font-size: 24px;"></i>
        </a>
        <a-menu slot="overlay">
          <a-menu-item>
          <a-button type="link" @click="updateEmail(index)"><a-icon type="mail"/> 邮箱修改</a-button>
        </a-menu-item>
      <a-menu-item>
        <a-button type="link" @click="updatePassword(index)"><a-icon type="lock"/> 密码修改</a-button>
      </a-menu-item>
      <a-menu-item>
        <a-button type="link" @click="updateRoles(index)"><a-icon type="gateway"/> 权限修改</a-button>
      </a-menu-item>
          <a-menu-item>
        <a-button type="link" @click="remove(record.id)"><a-icon type="delete"/> 删除</a-button>
      </a-menu-item>
    </a-menu>
  </a-dropdown>
    </span>
    </a-table>

    <a-modal
        title="添加操作员"
        :cancelText="'取消'"
        :okText="'确认'"
        :visible="addVisible"
        @ok="submit"
        @cancel="addVisible = false"
    >
      <a-form-model :model="selectAdmin">
        <a-form-model-item label="邮箱">
          <a-input v-model="selectAdmin.email"/>
        </a-form-model-item>
        <a-form-model-item label="密码">
          <a-input v-model="selectAdmin.password"/>
        </a-form-model-item>
      </a-form-model>
    </a-modal>

    <a-modal
        title="密码修改"
        :visible="passwordVisible"
        :cancelText="'取消'"
        :okText="'确认'"
        @ok="submit"
        @cancel="passwordVisible = false"
    >
      <a-form-model :model="selectAdmin">
        <a-form-model-item label="密码">
          <a-input v-model="selectAdmin.password"/>
        </a-form-model-item>
      </a-form-model>
    </a-modal>

    <a-modal
        title="邮箱修改"
        :visible="emailVisible"
        :cancelText="'取消'"
        :okText="'确认'"
        @ok="submit"
        @cancel="emailVisible = false"
    >
      <a-form-model :model="selectAdmin">
        <a-form-model-item label="绑定邮箱">
          <a-input v-model="selectAdmin.email"/>
        </a-form-model-item>
      </a-form-model>
    </a-modal>

    <a-modal
        title="权限修改"
        width="500px"
        :visible="rolesVisible"
        :cancelText="'取消'"
        :okText="'确认'"
        @ok="submitRoles"
        @cancel="rolesVisible = false"
    >
      <a-select
          v-model="selectRoles"
          mode="multiple"
          style="width: 100%"
          placeholder="选择权限"
          option-label-prop="label"
      >
        <a-select-option :value="item.value"
                         :label="item.value"
                         v-for="(item ,index) in roles"
                         :key="index">
          <span role="img" :aria-label="item.value">🇨🇳</span>{{ item.value + ' (描述: ' + item.description + ')' }}
        </a-select-option>
      </a-select>
    </a-modal>

  </div>
</template>
<script>
import {DeleteAdmin, FindAllAdmin, SaveAdmin} from "../../api/admin";
import {FindAllRole} from "../../api/role";

const columns = [
  {
    dataIndex: 'email',
    key: 'email',
    slots: {title: 'customTitle'},
    scopedSlots: {customRender: 'email'},
  },
  {
    title: '密码',
    dataIndex: 'password',
    key: 'password',
  },
  {
    title: '添加日期',
    key: 'createAt',
    dataIndex: 'createAt',
    scopedSlots: {customRender: 'createAt'},
  },
  {
    title: '操作',
    key: 'action',
    scopedSlots: {customRender: 'action'},
  },
  {
    title: '权限',
    dataIndex: 'roles',
    key: 'roles',
  },
];

export default {
  data() {
    return {
      roles: [],
      selectRoles: [],
      admins: [],
      loading: false,
      addVisible: false,
      emailVisible: false,
      passwordVisible: false,
      rolesVisible: false,
      columns,
      selectAdmin: {},
    };
  },

  mounted() {
    this.load()
  },

  methods: {
    clearInput() {
      this.selectAdmin = {}
    },
    load() {
      this.loading = true
      FindAllRole().then((res) => {
        if (res.status) this.roles = res.data
      })
      FindAllAdmin().then((res) => {
        setTimeout(() => {
          this.admins = res.data
          this.loading = false
        }, 600)
      })
    },

    submit() {
      SaveAdmin(this.selectAdmin).then((res) => {
        if (res.status) this.$message.success("操作员信息更新成功")
        this.addVisible = false
        this.emailVisible = false
        this.rolesVisible = false
        this.passwordVisible = false
        this.load()
      })
    },

    submitRoles() {
      let str = ''
      for (let i = 0; i < this.selectRoles.length; i++) {
        if (i === this.selectRoles.length - 1) {
          str += this.selectRoles[i]
        } else {
          str += this.selectRoles[i] + ";"
        }
      }
      this.selectAdmin.roles = str
      this.submit()
    },

    updateEmail(index) {
      this.emailVisible = true
      this.selectAdmin = this.admins[index]
    },

    updatePassword(index) {
      this.passwordVisible = true
      this.selectAdmin = this.admins[index]
    },

    updateRoles(index) {
      this.rolesVisible = true
      this.selectAdmin = this.admins[index]
      this.selectRoles = this.selectAdmin.roles.split(";")
      console.log(this.selectRoles)
    },

    remove(id) {
      DeleteAdmin(id).then((res) => {
        if (res.status) this.$message.success("操作员删除成功")
        this.load()
      })
    },


  },

};
</script>

<style scoped>
.editable-add-btn {
  margin-bottom: 15px;
}
</style>
