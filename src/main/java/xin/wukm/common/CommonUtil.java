/**
 * Create with IntelliJ IDEA
 * Project name : component
 * Package name : xin.wukm.common
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-4-25
 * Time : 上午11:03
 * ---------------------------------
 */
package xin.wukm.common;

import com.google.common.base.Strings;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Create with IntelliJ IDEA
 * Project name : component
 * Package name : xin.wukm.common
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-4-25
 * Time : 上午11:03
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class CommonUtil {

    /**
     * @since   JDK1.8
     */
    private CommonUtil(){
        //不支持共有构造
    }

    /**
     * Returns a formatted string using the specified locale, format string,
     * and arguments.
     *
     * @param  format
     *         A <a href="../util/Formatter.html#syntax">format string</a>
     *
     * @param  args
     *         Arguments referenced by the format specifiers in the format
     *         string.  If there are more arguments than format specifiers, the
     *         extra arguments are ignored.  The number of arguments is
     *         variable and may be zero.  The maximum number of arguments is
     *         limited by the maximum dimension of a Java array as defined by
     *         <cite>The Java&trade; Virtual Machine Specification</cite>.
     *         The behaviour on a
     *         {@code null} argument depends on the
     *         <a href="../util/Formatter.html#syntax">conversion</a>.
     *
     * @throws  java.util.IllegalFormatException
     *          If a format string contains an illegal syntax, a format
     *          specifier that is incompatible with the given arguments,
     *          insufficient arguments given the format string, or other
     *          illegal conditions.  For specification of all possible
     *          formatting errors, see the <a
     *          href="../util/Formatter.html#detail">Details</a> section of the
     *          formatter class specification
     *
     * @return  A formatted string
     *
     * @see  java.util.Formatter
     * @since  1.5
     */
    public static String formatString(String format, Object... args){
        return String.format(format,args);
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex string.
     *
     * @param input
     *            Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(String input){
        byte[] values = DigestUtils.getMd5Digest().digest(input.getBytes());
        return DigestUtils.md5Hex(values);
    }

    /**
     * Formats a Date into a date/time string.
     * now the time value to be formatted into a time string.
     * @return the formatted time string.
     */
    public static String formatNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * Static factory to retrieve a type 4 (pseudo randomly generated) UUID.
     *
     * The {@code UUID} is generated using a cryptographically strong pseudo
     * random number generator.
     *
     * @return  A randomly generated {@code UUID}
     */
    public static String uuid(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public static String token(){
        return uuid().replace("-",randomHexString(5)).replaceAll("-","").toUpperCase();
    }

    public static String randomHexString(){
        return randomHexString(25);
    }

    /**
     * <p>Creates a random string whose length is the number of characters
     * specified.</p>
     *
     * <p>Characters will be chosen from the set of characters whose
     * ASCII value is between {@code 32} and {@code 126} (inclusive).</p>
     *
     * @param count  the length of random string to create
     * @return the random string
     */
    public static String randomString(int count){
        return RandomStringUtils.randomAscii(count);
    }

    /**
     * <p>Creates a random string whose length is the number of characters
     * specified.</p>
     *
     * <p>Characters will be chosen from the set of numeric
     * characters.</p>
     *
     * @param count  the length of random string to create
     * @return the random string
     */
    public static String randomNumeric(int count){
        return RandomStringUtils.randomNumeric(count);
    }

    /**
     * <p>Creates a random string whose length is the number of characters
     * specified.</p>
     *
     * <p>Characters will be chosen from the set of characters specified.</p>
     *
     * @param count  the length of random string to create
     *  chars  the character array containing the set of characters to use,
     *  [0-9A-F]
     * @return the random string
     * @throws IllegalArgumentException if {@code count} &lt; 0.
     */
    public static String randomHexString(int count){
        return RandomStringUtils.random(count,'0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9','A', 'B', 'C', 'D', 'E', 'F');
    }

    /**
     * <p>Copy property values from the origin bean to the destination bean
     * for all cases where the property names are the same.</p>
     *
     * <p>For more details see <code>BeanUtilsBean</code>.</p>
     *
     * @param dest Destination bean whose properties are modified
     * @param orig Origin bean whose properties are retrieved
     *
     * @throws IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @throws IllegalArgumentException if the <code>dest</code> or
     *  <code>orig</code> argument is null or if the <code>dest</code>
     *  property type is different from the source type and the relevant
     *  converter has not been registered.
     * @throws InvocationTargetException if the property accessor method
     *  throws an exception
     * @see BeanUtilsBean#copyProperties
     */
    public static void copyProperties(Object dest, Object orig){
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (IllegalAccessException e){
            System.out.println(e.getCause());
        } catch (InvocationTargetException e){
            System.out.println(e.getCause());
        }
    }

    /**
     * Returns the given string if it is nonempty; {@code null} otherwise.
     *
     * @param string the string to test and possibly return
     * @return {@code string} itself if it is nonempty; {@code null} if it is
     *     empty or null
     */
    public static String emptyToNull(String string){
        return Strings.emptyToNull(string);
    }

    /**
     * Returns the given string if it is non-null; the empty string otherwise.
     *
     * @param string the string to test and possibly return
     * @return {@code string} itself if it is non-null; {@code ""} if it is null
     */
    public static String nullToEmpty(String string){
        return Strings.nullToEmpty(string);
    }

    /**
     * Returns {@code true} if the given string is null or is the empty string.
     *
     * <p>Consider normalizing your string references with {@link #nullToEmpty}.
     * If you do, you can use {@link String#isEmpty()} instead of this
     * method, and you won't need special null-safe forms of methods like {@link
     * String#toUpperCase} either. Or, if you'd like to normalize "in the other
     * direction," converting empty strings to {@code null}, you can use {@link
     * #emptyToNull}.
     *
     * @param string a string reference to check
     * @return {@code true} if the string is null or is the empty string
     */
    public static boolean isNullOrEmpty(String string){
        return Strings.isNullOrEmpty(string);
    }


    /**
     * Bean to Map<String,Object>
     * @param bean
     * @return
     */
    public static Map<String,Object> beanToMap(Object bean){
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> source = new BeanMap(bean);
        for(Object key:source.keySet()){
            result.put(String.valueOf(key),source.get(key));
        }
        return result;
    }
}
