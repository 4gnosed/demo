package mybatisx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.example.demo.main.Dog;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
//@Data
public class UserModel extends Dog implements Serializable {
    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 加密后密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 盐
     */
    @TableField(value = "salt")
    private String salt;

    /**
     * 上次登录时间
     */
    @TableField(value = "last_login")
    private Date last_login;

    /**
     * 状态  0：禁用   1：正常
     */
    @TableField(value = "enabled")
    private Integer enabled;

    /**
     * 真实姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮件
     */
    @TableField(value = "email")
    private String email;

    /**
     * 头像路径
     */
    @TableField(value = "avatar_path")
    private String avatar_path;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

//    @Override
//    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        }
//        if (that == null) {
//            return false;
//        }
//        if (getClass() != that.getClass()) {
//            return false;
//        }
//        UserModel other = (UserModel) that;
//        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
//            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
//            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
//            && (this.getSalt() == null ? other.getSalt() == null : this.getSalt().equals(other.getSalt()))
//            && (this.getLast_login() == null ? other.getLast_login() == null : this.getLast_login().equals(other.getLast_login()))
//            && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()))
//            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
//            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
//            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
//            && (this.getAvatar_path() == null ? other.getAvatar_path() == null : this.getAvatar_path().equals(other.getAvatar_path()));
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
//        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
//        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
//        result = prime * result + ((getSalt() == null) ? 0 : getSalt().hashCode());
//        result = prime * result + ((getLast_login() == null) ? 0 : getLast_login().hashCode());
//        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
//        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
//        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
//        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
//        result = prime * result + ((getAvatar_path() == null) ? 0 : getAvatar_path().hashCode());
//        return result;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", salt=").append(salt);
        sb.append(", last_login=").append(last_login);
        sb.append(", enabled=").append(enabled);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", avatar_path=").append(avatar_path);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}