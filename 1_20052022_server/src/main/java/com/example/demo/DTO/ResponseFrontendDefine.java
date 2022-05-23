package com.example.demo.DTO;

public class ResponseFrontendDefine {
    // thành công
    public static final int CODE_SUCCESS = 0;

    // exception không có quyền truy cập tài nguyên
    public static final int CODE_PERMISSION = 1;

    // exception không tìm thấy entity
    public static final int CODE_NOT_FOUND = 2;

    // exception name/code đã tồn tại
    public static final int CODE_ALREADY_EXIST = 3;
}
