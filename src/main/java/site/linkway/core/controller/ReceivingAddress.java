package site.linkway.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.linkway.core.entity.vo.AddressList;
import site.linkway.core.service.ReceivingAddressService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*收货地址模块*/
@Controller
@RequestMapping("/api/user")
public class ReceivingAddress {
    static Logger logger= Logger.getLogger(ReceivingAddress.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ReceivingAddressService receivingAddressService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    HttpServletResponse httpServletResponse;
    @Autowired
    HttpSession httpSession;

    /*添加新收货地址*/
    @PostMapping(value = "/addresses",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addAddress(@NonNull String phone,
                             @NonNull String name,
                             @NonNull String address,
                             @SessionAttribute("id") String email
                             ) throws JsonProcessingException {
        AddressList addressList=new AddressList();
        addressList.setResult(receivingAddressService.add(email,phone,name,address));
        addressList.setAddresses(receivingAddressService.getAll(email));
        return mapper.writeValueAsString(addressList);
    }

    /*删除收货地址*/
    @DeleteMapping(value = "/addresses",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteAddress(@NonNull String addressId,
                                @SessionAttribute("id") String email
                                ) throws JsonProcessingException {
        AddressList addressList=new AddressList();
        addressList.setResult(receivingAddressService.del(email,addressId));
        addressList.setAddresses(receivingAddressService.getAll(email));
        return mapper.writeValueAsString(addressList);

    }

    /*获得自己存储的收货地址*/
    @GetMapping(value = "/addresses",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getMyAddress(@SessionAttribute("id") String email
                                ) throws JsonProcessingException {

        AddressList addressList=new AddressList();
        addressList.setAddresses(receivingAddressService.getAll(email));
        return mapper.writeValueAsString(addressList);
    }

}
