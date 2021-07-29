package com.example.demo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.domain.common.PageQuery;
import com.example.demo.domain.common.PageResult;
import com.example.demo.domain.common.ResponseResult;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.dto.UserQueryDTO;
import com.example.demo.domain.entity.UserDo;
import com.example.demo.service.UserService;
import com.example.demo.util.ValidatorUtils;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(UserDTO userDTO) {
        UserDo userDo = new UserDo();
        //TODO 潜拷贝，属性名相同才能拷贝
        BeanUtils.copyProperties(userDTO, userDo);
        return userMapper.insert(userDo);
    }

    @Override
    public int update(Long id, UserDTO userDTO) {
        UserDo userDo = new UserDo();

        BeanUtils.copyProperties(userDTO, userDo);

        userDo.setId(id);
        return userMapper.updateById(userDo);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery) {

        //手动校验功能
        ValidatorUtils.validate(pageQuery);

        //参数构造
        Page page = new Page(pageQuery.getPaveNo(), pageQuery.getPageSize());
        UserDo query = new UserDo();
        BeanUtils.copyProperties(pageQuery.getQuery(),query);
        //TODO 如果属性不一致，需要做特殊处理
        QueryWrapper queryWrapper = new QueryWrapper(query);

        //查询
        IPage<UserDo> userDoIPage = userMapper.selectPage(page, queryWrapper);

        //结果解析
        PageResult pageResult = new PageResult();
        pageResult.setPageNo((int) userDoIPage.getCurrent());
        pageResult.setPageSize((int) userDoIPage.getSize());
        pageResult.setTotal(userDoIPage.getTotal());
        pageResult.setPageNum(userDoIPage.getPages());

        //对数据进行转换
        List<UserDTO> userDTOList = Optional.ofNullable(userDoIPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDo -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(userDo, userDTO);
                    return userDTO;
                }).collect(Collectors.toList());

        pageResult.setData(userDTOList);



        return pageResult;
    }
}
