/**
 * Create with IntelliJ IDEA
 * Project name : component
 * Package name : xin.wukm.component
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-4-25
 * Time : 上午11:18
 * ---------------------------------
 */
package xin.wukm.component;

import org.apache.commons.beanutils.BeanMap;
import xin.wukm.common.CommonUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Create with IntelliJ IDEA
 * Project name : component
 * Package name : xin.wukm.component
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-4-25
 * Time : 上午11:18
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class Commons {

    /**
     * Compiles the given regular expression and attempts to match the given
     * input against it.
     *
     * <p> An invocation of this convenience method of the form
     *
     * <blockquote><pre>
     * Pattern.matches(regex, input);</pre></blockquote>
     *
     * behaves in exactly the same way as the expression
     *
     * <blockquote><pre>
     * Pattern.compile(regex).matcher(input).matches()</pre></blockquote>
     *
     * <p> If a pattern is to be used multiple times, compiling it once and reusing
     * it will be more efficient than invoking this method each time.  </p>
     *
     * @param  pattern
     *         The expression to be compiled
     *
     * @param  input
     *         The character sequence to be matched
     * @return whether or not the regular expression matches on the input
     * @throws PatternSyntaxException
     *          If the expression's syntax is invalid
     */
    public boolean matches(String input,String pattern){
        if(CommonUtil.isNullOrEmpty(pattern)){
            return true;
        }
        return Pattern.matches(pattern,input);
    }


    /**
     * Bean to Map<String,Object>
     *
     * @param bean
     * @return
     */
    public Map<String,Object> beanToMap(Object bean){
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> source = new BeanMap(bean);
        for(Object key:source.keySet()){
            result.put(String.valueOf(key),source.get(key));
        }
        return result;
    }
}
