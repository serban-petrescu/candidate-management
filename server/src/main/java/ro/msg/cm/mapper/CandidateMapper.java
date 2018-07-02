package ro.msg.cm.mapper;

import org.apache.commons.beanutils.BeanUtils;
import ro.msg.cm.model.Candidate;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CandidateMapper {

    private CandidateMapper() {
    }

    public static Candidate map(Map<String, Object> patchMap, Candidate candidate) {

        for (Map.Entry<String, Object> entry : patchMap.entrySet()) {
            try {
                BeanUtils.setProperty(candidate, entry.getKey(), entry.getValue());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return candidate;
    }

}
