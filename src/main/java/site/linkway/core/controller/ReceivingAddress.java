package site.linkway.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.linkway.core.entity.vo.AddressList;
import site.linkway.core.service.ReceivingAddressService;
import site.linkway.core.service.UserDataService;

import javax.servlet.http.HttpSession;

/*收货地址模块*/
@Controller
@RequestMapping("/api")
public class ReceivingAddress {
    static Logger logger= Logger.getLogger(ReceivingAddress.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ReceivingAddressService receivingAddressService;

    /*添加新收货地址*/
    @PostMapping(value = "/addresses",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addAddress(@NonNull String phone,
                             @NonNull String name,
                             @NonNull String address,
                             @NonNull HttpSession httpSession) throws JsonProcessingException {
        AddressList addressList=new AddressList();
        String email=(String)httpSession.getAttribute("id");
        addressList.setResult(receivingAddressService.add(email,phone,name,address));
        addressList.setAddresses(receivingAddressService.getAll(email));
        return mapper.writeValueAsString(addressList);
    }

    /*删除收货地址*/
    @DeleteMapping(value = "/addresses/{addressId}",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteAddress(@NonNull String addressId,
                                @NonNull HttpSession httpSession) throws JsonProcessingException {
        String email=(String)httpSession.getAttribute("id");
        AddressList addressList=new AddressList();
        addressList.setResult(receivingAddressService.del(email,addressId));
        addressList.setAddresses(receivingAddressService.getAll(email));
        return mapper.writeValueAsString(addressList);
    }

    /*获得自己存储的收货地址*/
    @GetMapping(value = "/addresses",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getMyAddress(@NonNull HttpSession httpSession) throws JsonProcessingException {
        String email=(String)httpSession.getAttribute("id");
        AddressList addressList=new AddressList();
        addressList.setAddresses(receivingAddressService.getAll(email));
        return mapper.writeValueAsString(addressList);
    }

}
