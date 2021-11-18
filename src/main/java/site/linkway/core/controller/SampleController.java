package site.linkway.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.linkway.core.entity.po.SamplePo;
import site.linkway.core.service.SampleService;

import java.util.List;
import org.apache.log4j.Logger;

@Controller
public class SampleController {
    static Logger logger= Logger.getLogger(SampleController.class);
//    @Autowired
//    @Qualifier("SampleServiceImpl")
//    private SampleService goodService;
//    @RequestMapping(value = "/selectAll",produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public String selectAll() throws Exception{
//        List<SamplePo> goods=goodService.selectAll();
//        for(int i=0;i<goods.size();i++){
//            System.out.println(goods.get(i).toString());
//        }
//        return "CONSOLE PRINT OVER";
//    }
//    @RequestMapping(value = "/selectById",produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public String selectById(int id) throws Exception{
//        SamplePo find=new SamplePo();
//        find.setId(id);
//        SamplePo good=goodService.selectById(find);
//        System.out.println(good.toString());
//        return "CONSOLE PRINT OVER";
//    }
}
