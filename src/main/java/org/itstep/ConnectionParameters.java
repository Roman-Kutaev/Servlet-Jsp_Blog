package org.itstep;

public enum  ConnectionParameters {
        URL("jdbc:mysql://localhost:3306/Blog"),
        USER("root"),
        PASSWORD("");

        public final String value;

        ConnectionParameters(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
}
