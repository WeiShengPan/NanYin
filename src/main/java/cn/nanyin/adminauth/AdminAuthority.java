package cn.nanyin.adminauth;

import java.lang.annotation.*;

/**
 * Created by MYNP on 2015/7/27 0027.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminAuthority {
    AuthorityType[] authorityTypes();
    ResultTypeEnum resultType() default ResultTypeEnum.page;
}
