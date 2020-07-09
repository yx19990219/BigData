<template>
    <div class="user">
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span><strong>When To Fly</strong></span>
            </div>
            <el-form label-width="100px"
                     size="small ">
                <el-row :gutter="24">
                    <el-col :span="4">
                        <el-form-item label="出发地:">
                            <el-input
                                    placeholder="departure"
                                    v-model="searchParams.cityFromName">
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="4">
                        <el-form-item label="目的地:">
                            <el-input
                                    placeholder="destination"
                                    v-model="searchParams.cityToName">
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="24" style="float: right">
                    <el-col :span="1.5">
                        <el-button type="primary" @click="getEchartsFight()">查询</el-button>
                    </el-col>
                </el-row>
            </el-form>
        </el-card>
        <!--echarts好久飞图表-->
        <div>
            <charts-view ref="sjtj1" :dataList="figureData" v-on:charts-click="handleClickCharts"></charts-view>
        </div>
        <div>
            <chart-zhuzhuang ref="sjtj2" :dataList="figureDetail" v-on:charts-click="handleClickCharts"></chart-zhuzhuang>
        </div>
        <div class="pagination">
            <el-pagination background layout="prev, pager, next" :pager-count="8" :total="total"
                           :hide-on-single-page="false" @current-change="handleCurrentChange">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import {getEchartsFight,getFlight} from "../utils/api";
    import ChartsView from "../components/ChartsView";
    import  ChartZhuzhuang from "../components/ChartZhuzhuang";
    export default {
        components: {
            ChartsView,ChartZhuzhuang
        },
        name: "User",
        data() {
            return {
                prices: [],
                depDate: [],
                total: 0,
                user: {
                    role: {}
                },
                flightList:[],
                editDialogVisible: false,
                addDialogVisible: false,
                searchParams: {},
                figureData: [
                ],
                figureDetail: [
                    // {title: '航空公司1-2', count: 10},
                    // {title: '航空公司1-3', count: 40},
                    // {title: '航空公司1-4', count: 30},
                    // {title: '航空公司1-5', count: 20},
                    // {title: '航空公司1-6', count: 20},
                    // {title: '航空公司1-7', count: 20},
                ],

            }
        },
        created() {
        },
        mounted() {
            this.getEchartsFight()
        },
        methods: {
            handleCurrentChange(pageNumber) {
                this.searchParams.pageNumber = pageNumber;
                this.handleGetFlight();
            },
            getEchartsFight() {
                getEchartsFight(this.searchParams).then(response => {
                    if (response && response.status === "success") {
                        let d = response.object
                        this.figureData=[];
                        for(var i=0;i<d.length;i++){
                            let f = d[i];
                            let dt = f.depDate;
                            let price = f.price;
                            this.figureData.push({title:dt,count:price})
                        }
                    }
                })
            },
            handleGetFlight(){
                getFlight(this.searchParams).then(response=>{
                    if (response && response.status === "success") {
                        this.total = response.total
                        let d = response.object
                        this.figureDetail=[]
                        for(var i=0;i<d.length;i++){
                            let f = d[i];
                            let name = f.flightCompanyName+'|'+f.flightNo;
                            let price = f.price;
                            this.figureDetail.push({title:name,count:price,d:f})
                        }
                    }
                });
            },
            handleClickCharts(dt){
                this.searchParams.depDate=dt;
                this.handleGetFlight()
            },

        }
    }
</script>

<style scoped>
    .el-pagination {
        margin-right: auto;
    }

    .menu {
        margin-top: 20px;
        display: flex;
    }

    .el-row {
        margin-bottom: 20px;

    &
    :last-child {
        margin-bottom: 0;
    }

    }
    .el-col {
        border-radius: 4px;
    }

    .bg-purple-dark {
        background: #DCDFE6;
    }

    .bg-purple {
        background: #d3dce6;
    }

    .bg-purple-light {
        background: #e5e9f2;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }

    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
    }
</style>