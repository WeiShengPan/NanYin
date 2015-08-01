package cn.nanyin.adminauth;

/**
 * Created by MYNP on 2015/7/27 0027.
 */
public class AuthorityHelper {
    public static boolean hasAuthority(int akey,String string){
        if(string==null || "".equals(string)){
            return false;
        }
        char value = string.charAt(akey);
        if(value == '1'){
            return true;
        }
        return false;
    }
}
