<template>
  <el-col span="300">
    <el-tabs v-model="activeName" @tab-click="Click">
      <el-tab-pane label="粉丝列表" name="first">
        <el-input placeholder="搜索粉丝" v-model="value">
          <el-button slot="append" icon="el-icon-search" @click="Search(1)"></el-button>
        </el-input>
        <el-table
            :data="sfollowers.slice((currentPage-1)*pagesize,currentPage*pagesize)"
            border
            stripe
        >
          <el-table-column type="index"></el-table-column>
          <el-table-column width="150" label="用户名" prop="username"></el-table-column>
          <el-table-column width="200" label="个人简介" prop="intro"></el-table-column>
          <el-table-column width="100" label="操作" prop="id">
            <template slot-scope="scope">
              <el-button
                  type="text"
                  size="small"
                  prop="id"
                  @click="removefollower(scope.row)"
              >
                <p>移除粉丝</p>
              </el-button>
            </template>
          </el-table-column>
          <el-table-column width="100" label="操作" prop="id">
            <template slot-scope="scope">
              <el-button type="text" size="small" align="left" prop="id" @click="visitor(scope.row)">
                <p>访问主页</p>
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[5, 10, 20, 40]"
            :page-size="pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="sfollowers.length"
        ></el-pagination>
      </el-tab-pane>
      <el-tab-pane label="关注列表" name="second">
        <el-input placeholder="搜索关注" v-model="value">
          <el-button slot="append" icon="el-icon-search" @click="Search(2)"></el-button>
        </el-input>
        <el-table
            :data="sfollowings.slice((currentPage1-1)*pagesize1,currentPage1*pagesize1)"
            border
            stripe
        >
          <el-table-column type="index"></el-table-column>
          <el-table-column width="150" label="用户名" prop="username"></el-table-column>
          <el-table-column width="200" label="个人简介" prop="intro"></el-table-column>
          <el-table-column width="100" label="操作" prop="id">
            <template slot-scope="scope">
              <el-button
                  type="text"
                  size="small"
                  prop="id"
                  @click="removefollowing(scope.row)"
              >
                <p>取消关注</p>
              </el-button>
            </template>
          </el-table-column>
          <el-table-column width="100" label="操作" prop="id">
            <template slot-scope="scope">
              <el-button type="text" size="small" prop="id" @click="visitor(scope.row)">
                <p>访问主页</p>
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
            @size-change="handleSizeChange1"
            @current-change="handleCurrentChange1"
            :current-page="currentPage1"
            :page-sizes="[5, 10, 20, 40]"
            :page-size="pagesize1"
            layout="total, sizes, prev, pager, next, jumper"
            :total="sfollowings.length"
        ></el-pagination>
      </el-tab-pane>
    </el-tabs>
  </el-col>
</template>