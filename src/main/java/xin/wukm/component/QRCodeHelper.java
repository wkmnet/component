/**
 * Create with IntelliJ IDEA
 * Project name : component
 * Package name : xin.wukm.component
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-4-25
 * Time : 上午11:19
 * ---------------------------------
 */
package xin.wukm.component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import xin.wukm.common.CommonUtil;
import xin.wukm.common.ConstantUtil;
import xin.wukm.model.LogoImageInMatrixConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;

/**
 * Create with IntelliJ IDEA
 * Project name : component
 * Package name : xin.wukm.component
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-4-25
 * Time : 上午11:19
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class QRCodeHelper {

    /**
     * 根据内容生成二维码数据
     * @param content 二维码文字内容[为了信息安全性，一般都要先进行数据加密]
     * @param width 二维码照片宽度
     * @param height 二维码照片高度
     * @return
     */
    public BitMatrix createQRCode(String content, int width, int height){
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        //设置字符编码
        hints.put(EncodeHintType.CHARACTER_SET, ConstantUtil.CHARSET_UTF_8);
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            System.out.println(e.getMessage());
        }
        return matrix;
    }

    /**
     * 写入二维码、以及将照片logo写入二维码中
     * @param matrix 要写入的二维码
     * @param format 二维码照片格式
     * @param imagePath 二维码照片保存路径
     * @param logoPath logo路径
     * @throws IOException
     */
    public void writeToFile(BitMatrix matrix, String format, String imagePath, String logoPath) {
        Path path = Paths.get(imagePath);
        try {
            MatrixToImageWriter.writeToPath(matrix, format, path, new MatrixToImageConfig());
            //添加logo图片, 此处一定需要重新进行读取，而不能直接使用二维码的BufferedImage 对象
            if(CommonUtil.isNullOrEmpty(logoPath)){
                return;
            }
            Path logo = Paths.get(logoPath);
            if(!logo.toFile().isFile()){
                return;
            }
            BufferedImage img = ImageIO.read(new File(imagePath));
            overlapImage(img, format, imagePath, logoPath, new LogoImageInMatrixConfig());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * 将照片logo添加到二维码中间
     * @param image 生成的二维码照片对象
     * @param imagePath 照片保存路径
     * @param logoPath logo照片路径
     * @param format 照片格式
     */
    public void overlapImage(BufferedImage image, String format, String imagePath, String logoPath, LogoImageInMatrixConfig logoConfig) {
        try {
            BufferedImage logo = ImageIO.read(new File(logoPath));
            Graphics2D g = image.createGraphics();
            //考虑到logo照片贴到二维码中，建议大小不要超过二维码的1/5;
            int width = image.getWidth() / logoConfig.getLogoPart();
            int height = image.getHeight() / logoConfig.getLogoPart();
            //logo起始位置，此目的是为logo居中显示
            int x = (image.getWidth() - width) / 2;
            int y = (image.getHeight() - height) / 2;
            //绘制图
            g.drawImage(logo, x, y, width, height, (Image imgs, int infoflags,
                                                    int xx, int yy, int widths, int heights) ->{
                System.out.println(imgs.toString());
                System.out.println("xx:" + xx);
                System.out.println("yy:" + yy);
                System.out.println("widths:" + widths);
                System.out.println("heights:" + heights);
                return true;
            });

            //给logo画边框
            //构造一个具有指定线条宽度以及 cap 和 join 风格的默认值的实心 BasicStroke
            g.setStroke(new BasicStroke(logoConfig.getBorderWidth()));
            g.setColor(logoConfig.getBorderColor());
            g.drawRect(x, y, width, height);

            g.dispose();
            //写入logo照片到二维码
            ImageIO.write(image, format, new File(imagePath));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
