package com.example.demo.service;

import com.example.demo.domain.common.PageQuery;
import com.example.demo.domain.common.PageResult;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.dto.UserQueryDTO;

import java.util.List;

public interface UserService {
    /**
     * add
     * @param userDTO
     * @return
     */
    int save(UserDTO userDTO);

    /**
     * update
     * @param id
     * @param userDTO
     * @return
     */
    int update(Long id, UserDTO userDTO);

    /**
     * Delete
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * Get
     * @param pageQuery
     * @return
     */
    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery);
}
