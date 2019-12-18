package pl.dmcs.rkotas.service.common;

public interface ReCaptchaService {
    boolean verify(String captcha);

}
