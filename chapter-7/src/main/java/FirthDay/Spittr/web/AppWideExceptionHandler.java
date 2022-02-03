/**
 * @author 60417
 * @date 2022/1/30
 * @time 22:17
 * @todo
 */
package FirthDay.Spittr.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 编写一个类，能够处理所有控制器的所有方法可能抛出的DuplicateSpittleException的异常
 */
//使用通知注解，会处理requestmapping注解所注的控制器的所有方法
@ControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(DuplicateSpittleException.class)
    public String duplicateSpittleHandler(){
        return "error/duplicate";
    }

}
