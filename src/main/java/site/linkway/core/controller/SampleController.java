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
    @Autowired
    @Qualifier("SampleServiceImpl")
    private SampleService goodService;
    @RequestMapping(value = "/selectAll",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String selectAll() throws Exception{
        List<SamplePo> goods=goodService.selectAll();
        for(int i=0;i<goods.size();i++){
            System.out.println(goods.get(i).toString());
        }
        return "CONSOLE PRINT OVER";
    }
    @RequestMapping(value = "/selectById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String selectById(int id) throws Exception{
        SamplePo find=new SamplePo();
        find.setId(id);
        SamplePo good=goodService.selectById(find);
        System.out.println(good.toString());
        return "CONSOLE PRINT OVER";
    }
    @RequestMapping(value = "/add",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(String name,double price,String profile) throws Exception{
        SamplePo add=new SamplePo();
        add.setName(name);
        add.setPrice(price);
        add.setProfile(profile);
        long good=goodService.add(add);
        System.out.println("INSERT:\n"+add.toString());
        return "CONSOLE PRINT OVER";
    }
    @RequestMapping(value = "/updateById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateById(int id,String name,double price,String profile) throws Exception{
        SamplePo good=new SamplePo();
        good.setId(id);
        good.setName(name);
        good.setPrice(price);
        good.setProfile(profile);
        long line=goodService.updateById(good);
        System.out.println("UPDATE:\n"+good.toString());
        return "CONSOLE PRINT OVER";
    }
    @RequestMapping(value = "/deleteById",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteById(int id) throws Exception{
        SamplePo good=new SamplePo();
        good.setId(id);
        long line=goodService.deleteById(good);
        System.out.println(good.toString());
        return "CONSOLE PRINT OVER";
    }
}
