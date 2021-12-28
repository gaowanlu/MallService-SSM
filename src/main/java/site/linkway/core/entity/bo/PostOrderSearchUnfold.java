package site.linkway.core.entity.bo;

import lombok.Data;

import java.util.Date;

@Data
public class PostOrderSearchUnfold {
    String orderId;//订单号
    String userId;//订单所属者
    String status;//订单状态
    int pageSize;//每页多少个 1~20
    int pageNow;//现在所在页码 1~
    int size;//每页多少个 1~20
    int num;//检索起点
    Date min;//最小日期
    Date max;//最大日期
    public PostOrderSearchUnfold(PostOrderSearch postOrderSearch){
        this.orderId=postOrderSearch.getOrderId();
        this.userId= postOrderSearch.getEmail();
        this.status=postOrderSearch.getStatus();
        this.pageSize=postOrderSearch.getPageSize();
        this.pageNow=postOrderSearch.getPageNow();
        this.min=postOrderSearch.getTime().getMin();
        this.max=postOrderSearch.getTime().getMax();
        /*校验内容*/
        this.orderId=this.orderId.replace(" ","");
        if(this.orderId.equals("")){this.orderId=null;}
        this.userId=this.userId.replace(" ","");
        if(this.userId.equals("")){this.userId=null;}
        this.status=this.status.replace(" ","");
        if(this.status.equals("")){this.status=null;}
        this.pageSize=(pageSize>=1&&pageSize<=20)?pageSize:20;
        this.pageNow=(pageNow>=1)?pageNow:1;
        /*计算检索起点与页大小*/
        this.size=this.pageSize;
        this.num=(this.pageNow-1)*this.pageSize;
    }
}
