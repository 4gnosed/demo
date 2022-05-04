package mybatisx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mybatisx.entity.UserModel;
import mybatisx.service.UserService;
import mybatisx.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author gnosed
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-05-04 16:23:28
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel>
    implements UserService{

}




