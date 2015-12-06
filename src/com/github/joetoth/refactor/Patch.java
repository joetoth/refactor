package com.github.joetoth.refactor;

/**
 * Created by joetoth on 12/3/15.
 */
@Deprecated
public class Patch {
    Object o;
    Patch(Object o) {
        this.o = o;
    }

    private void some() {
        o.toString();
    }
}
