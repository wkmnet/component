/**
 * Create with IntelliJ IDEA
 * Project name : component
 * Package name : xin.wukm.component
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-5-16
 * Time : 上午10:51
 * ---------------------------------
 */
package xin.wukm.component;

import org.apache.commons.lang3.BooleanUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Create with IntelliJ IDEA
 * Project name : component
 * Package name : xin.wukm.component
 * Author : Wukunmeng
 * User : wukm
 * Date : 17-5-16
 * Time : 上午10:51
 * ---------------------------------
 * To change this template use File | Settings | File and Code Templates.
 */
public class SensitiveWordComponent {

    private Map<String,Object> stringStringMap;

    public void initSensitiveWord(Set<String> words){
        if(stringStringMap == null){
            stringStringMap = new HashMap<>();
        }
        words.forEach((String key) ->{
            Map<String,Object> currentMap = stringStringMap;
            for(int i = 0;i < key.length();i++){
                String c = String.valueOf(key.charAt(i));
                Object temp = currentMap.get(c);
                if(temp == null){
                    Map<String,Object> node = new HashMap<String, Object>();
                    node.put("isEnd",false);
                    currentMap.put(c,node);
                    currentMap = node;
                } else {
                    currentMap = (Map<String,Object>)temp;
                }
                if(i == key.length() - 1){
                    currentMap.put("isEnd",true);
                }
            }
        });
    }

    public String toString(){
        return stringStringMap.toString();
    }


    private int checkSensitiveWord(String check,int beginIndex){
        Map<String,Object> current = stringStringMap;
        int endIndex = -1;
        for(int i = beginIndex;i < check.length();i++){
            String key = String.valueOf(check.charAt(i));
            Object node = current.get(key);
            if(node == null){
                i = check.length();
            } else {
                current = (Map<String,Object>) node;
                if(BooleanUtils.toBoolean(current.get("isEnd").toString())){
                    endIndex = i;
                }
            }
        }
        return endIndex;
    }

    public boolean containsSensitiveWord(String txt){
        return containsSensitiveWord(txt,0);
    }

    public boolean containsSensitiveWord(String txt, int beginIndex){
        int endIndex = checkSensitiveWord(txt,beginIndex);
        return beginIndex > 0;
    }

    public Set<String> getSensitiveWord(String txt,int beginIndex){
        Set<String> sensitiveWords = new HashSet<>();
        for(int i = beginIndex;i < txt.length();i++){
            int endIndex = checkSensitiveWord(txt,i);
            if(endIndex > 0){
                sensitiveWords.add(txt.substring(i,endIndex + 1));
                i = endIndex + 1;
            }
        }
        return sensitiveWords;
    }

    public Set<String> getSensitiveWord(String txt){
        return getSensitiveWord(txt,0);
    }
}
