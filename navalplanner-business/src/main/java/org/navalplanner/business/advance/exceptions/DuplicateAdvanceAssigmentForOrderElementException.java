package org.navalplanner.business.advance.exceptions;


/**
 * An exception for modeling a problem with duplicated advance assigment of the
 * same type for an order element. It contains a message, the key of the
 * instance, and its class name.
 * @author Susana Montes Pedreira <smontes@wirelessgalicia.com>
 */
@SuppressWarnings("serial")
public class DuplicateAdvanceAssigmentForOrderElementException extends
        Exception {

    private Object key;
    private String className;

    public DuplicateAdvanceAssigmentForOrderElementException(
            String specificMessage, Object key, String className) {
        super(specificMessage + " (key = '" + key + "' - className = '"
                + className + "')");
        this.key = key;
        this.className = className;
    }

    public DuplicateAdvanceAssigmentForOrderElementException(
            String specificMessage, Object key, Class<?> klass) {
        this(specificMessage, key, klass.getName());
    }

    public Object getKey() {
        return key;
    }

    public String getClassName() {
        return className;
    }
}
