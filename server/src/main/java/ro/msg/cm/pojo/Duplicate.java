package ro.msg.cm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.cm.types.DuplicateType;

import java.util.Set;

/**
 * POJO that keeping the ids of the duplicates of the original.
 * The duplicateType is based on what attribute is the same
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Duplicate {
    private long original;
    private Set<Long> ids;
    private DuplicateType duplicateType;
}
