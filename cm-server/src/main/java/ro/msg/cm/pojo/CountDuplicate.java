package ro.msg.cm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.cm.types.DuplicateType;

import java.util.Map;


/**
 * POJO that keeps a map containing the count of duplicateTypes.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountDuplicate {
    private long original;
    private Map<DuplicateType, Long> duplicateCount;
}
