package mybatisx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mybatisx.entity.Student;
import mybatisx.service.StudentService;
import mybatisx.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author gnosed
* @description 针对表【student】的数据库操作Service实现
* @createDate 2022-05-04 16:15:40
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}




