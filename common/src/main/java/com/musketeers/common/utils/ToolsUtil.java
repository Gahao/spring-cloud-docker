package com.musketeers.common.utils;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.musketeers.common.entity.BufferedImageLuminanceSource;
import com.musketeers.common.entity.MatrixToImageWriter;
import com.musketeers.common.entity.system.SysUser;
import com.musketeers.common.enums.SysEnum;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class ToolsUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().toUpperCase();
    }

    /**
     * uuid转guid
     * @param uuid
     * @return
     */
    public static String UUID2GUID(String uuid){
        return uuid.replace("-", "").toUpperCase();
    }

    /**
     * guid转uuid
     * @param guid
     * @return
     */
    public static String GUID2UUID(String guid){
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = guid.toCharArray();
        for(int i = 0; i < chars.length; i++){
            if(i == 8 || i == 12 || i == 16 || i == 20)
                stringBuilder.append('-');
            stringBuilder.append(chars[i]);
        }

        return stringBuilder.toString();
    }

    /**
     * MD5方法
     *
     * @param text 明文
     * @return 密文
     * @throws Exception
     */
    public static String getMD5(String text) throws Exception {
        StringBuffer sb = new StringBuffer();
        try {
            // 加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = text.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[] { '0', '1', '2', '3', '4', '5',
                    '6', '7' , '8', '9', 'A', 'B', 'C', 'D', 'E','F' };
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * base64加密
     * @param str
     * @return
     */
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    /**
     * base64解密
     * @param str
     * @return
     */
    public static String getFromBase64(String str) {
        byte[] b = null;
        String result = null;
        if (str != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(str);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 生成随机字符串
     * @param length
     * @return
     */
    public static String RandomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i ++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    /**
     * 根据session获取当前用户
     * @return
     * @throws Exception
     */
    public static SysUser getCurrentUser() throws Exception{
        //获取到当前线程绑定的请求对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //已经拿到session,就可以拿到session中保存的用户信息了。
        SysUser sysUser = (SysUser) request.getSession().getAttribute(SysEnum.USER_SESSION);

        return sysUser;
    }

    /**
     * 生成包含字符串信息的二维码图片
     * @param outputStream 文件输出流路径
     * @param content 二维码携带信息
     * @param qrCodeSize 二维码图片大小
     * @param imageFormat 二维码的格式
     * @throws WriterException
     * @throws IOException
     */
    public static boolean createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat) throws WriterException, IOException{
        //设置二维码纠错级别map
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  // 矫错级别
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //创建比特矩阵(位矩阵)的QR码编码的字符串
        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
        // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth-200, matrixWidth-200, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // 使用比特矩阵画并保存图像
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < matrixWidth; i++){
            for (int j = 0; j < matrixWidth; j++){
                if (byteMatrix.get(i, j)){
                    graphics.fillRect(i-100, j-100, 1, 1);
                }
            }
        }
        return ImageIO.write(image, imageFormat, outputStream);
    }

    /**
     * 读二维码并输出携带的信息
     */
    public static void readQrCode(InputStream inputStream) throws IOException{
        //从输入流中获取字符串信息
        BufferedImage image = ImageIO.read(inputStream);
        //将图像转换为二进制位图源
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result = null ;
        try {
            result = reader.decode(bitmap);
        } catch (ReaderException e) {
            e.printStackTrace();
        }
        System.out.println(result.getText());
    }

    /**
     * 生成二维码base64位流，默认大小400
     * 默认:png 格式
     *
     * @param text 内容
     */
    public static String createQRCodeBase64Default(String text) {
        return createQRCodeBase64(text,300);
    }

    /**
     * 生成二维码base64位流，可选大小
     * 默认:png 格式
     *
     * @param text 内容
     */
    public static String createQRCodeBase64(String text,int qrCodeSize) {
        Map<EncodeHintType, Object> hints = new Hashtable<>();
        String base64Img = "data:image/png;base64,";
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);  // 矫错级别
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            // 生成输出流
            BitMatrix byteMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(byteMatrix);
            base64Img = base64Img + encodeToString("png", image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64Img;
    }

    /**
     * 将图片转换成base64格式进行存储
     *
     * @param formatName 文件格式
     * @param image 图片流
     * @return base64字符串
     */
    private static String encodeToString(String formatName, BufferedImage image) {
        String imageString = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ImageIO.write(image, formatName, bos);
            byte[] imageBytes = bos.toByteArray();
            imageString = new String(Base64.encodeBase64(imageBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    /**
     * 测试代码
     * @throws WriterException
     */
    public static void main(String[] args) throws IOException, WriterException {
        System.out.println(createQRCodeBase64("WE1231238239128sASDASDSADSDWEWWREWRERWSDFDFSDSDF123123123123213123",300));
        /*String uuid = getUUID();
        System.out.println("新建uuid：" + uuid);
        System.out.println("转换成guid：" + UUID2GUID(uuid));
        System.out.println("转换成uuid：" + GUID2UUID(UUID2GUID(uuid)));*/
    }
}
