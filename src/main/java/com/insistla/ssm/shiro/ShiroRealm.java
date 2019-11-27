package com.insistla.ssm.shiro;

import com.insistla.ssm.dao.UserDao;
import com.insistla.ssm.pojo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * 用户权限认证
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;

    private String passWord; //用户密码
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Object principal = principalCollection.getPrimaryPrincipal();//获取登录的用户名
        if("admin".equals(principal)){
            info.addRole("admin");
        }
        if("user".equals(principal)){
            info.addRole("list");
        }
        info.addRole("user");
        return info;
    }

    /**
     * 身份验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1. token 中获取登录的 username! 注意不需要获取password.
        Object principal = token.getPrincipal();
        //2. 利用 username 查询数据库得到用户的信息.
        User user=userDao.selectUserByName((String) principal);
        if(user!=null){
            passWord = user.getUserPassword();
        }

        String credentials = passWord;
        //3.设置盐值 ，（加密的调料，让加密出来的东西更具安全性，一般是通过数据库查询出来的。
        // 简单的说，就是把密码根据特定的东西而进行动态加密，如果别人不知道你的盐值，就解不出你的密码）
        String source = "abcdefg";
        ByteSource credentialsSalt = new Md5Hash(source);

        //当前 Realm 的name
        String realmName = getName();
        //返回值实例化
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);

        return info;
    }

    //init-method 配置.
    public void setCredentialMatcher(){
        HashedCredentialsMatcher  credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");//MD5算法加密
        credentialsMatcher.setHashIterations(1024);//1024次循环加密
        setCredentialsMatcher(credentialsMatcher);
    }


    //MD5加密方法(注册时密码存入数据库时使用)
    public static String md5(String passWord){
        String saltSource = "blog"; //加的盐值
        String hashAlgorithmName = "MD5";
        Object salt = new Md5Hash(saltSource);
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, passWord, salt, hashIterations);
        String password = result.toString();
        return password;
    }

    //用来测试的算出密码password盐值加密后的结果，下面方法用于新增用户添加到数据库操作的
    public static void main(String[] args) {
        String saltSource = "abcdefg";
        String hashAlgorithmName = "MD5";
        String credentials = "123456789";
        Object salt = new Md5Hash(saltSource);
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }




}
