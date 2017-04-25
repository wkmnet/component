/**
 * Create with IntelliJ IDEA
 * Project name : component
 * Package name : xin.wukm.model
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-4-25
 * Time : 上午11:23
 * ---------------------------------
 */
package xin.wukm.model;

import com.google.common.base.Optional;

import java.awt.*;

/**
 * Create with IntelliJ IDEA
 * Project name : component
 * Package name : xin.wukm.model
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-4-25
 * Time : 上午11:23
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class LogoImageInMatrixConfig {

    private Integer logoPart = 5;

    private Color borderColor = Color.BLUE;

    private Integer borderWidth = 1;

    public LogoImageInMatrixConfig(){}

    public LogoImageInMatrixConfig(Integer logoPart,Color borderColor,Integer borderWidth){
        this.logoPart = Optional.of(logoPart).get();
        this.borderColor = Optional.of(borderColor).get();
        this.borderWidth = Optional.of(borderWidth).get();
    }

    public Integer getLogoPart() {
        return logoPart;
    }

    public void setLogoPart(Integer logoPart) {
        this.logoPart = logoPart;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Integer getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }
}
