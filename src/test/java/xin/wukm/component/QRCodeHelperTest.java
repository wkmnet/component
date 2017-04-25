/**
 * Create with IntelliJ IDEA
 * Project name : component
 * Package name : xin.wukm.component
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-4-25
 * Time : 上午11:26
 * ---------------------------------
 */
package xin.wukm.component;

import com.google.zxing.common.BitMatrix;
import org.junit.Test;
import xin.wukm.model.LogoImageInMatrixConfig;

import java.awt.*;

/**
 * Create with IntelliJ IDEA
 * Project name : component
 * Package name : xin.wukm.component
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-4-25
 * Time : 上午11:26
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class QRCodeHelperTest {

    @Test
    public void testQRCode(){
        QRCodeHelper helper = new QRCodeHelper();
        BitMatrix bitMatrix = helper.createQRCode("http://blog.wukm.xin",300,300);
        helper.writeToFile(bitMatrix,"jpg", "/home/wukm/qr_code.jpg", null);
    }
}
