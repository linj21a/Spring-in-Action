/**
 * @author 60417
 * @date 2022/1/30
 * @time 21:36
 * @todo
 */
package FirthDay.Spittr.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
//将异常映射为404状态码
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Spittles not Found")
public class SpittleNotFoundException extends RuntimeException{
}
