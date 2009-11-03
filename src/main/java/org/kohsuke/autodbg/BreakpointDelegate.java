package org.kohsuke.autodbg;

import com.sun.jdi.ThreadReference;
import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.event.BreakpointEvent;

/**
 * Object passed to the breakpoint closure as a delegate.
 *
 * <p>
 * This mostly mimicks a {@link ThreadReference} but also exposes some additional stuff.
 *
 * @author Kohsuke Kawaguchi
 */
public class BreakpointDelegate extends ThreadReferenceFilter {
    private ThreadVariables vars;

    BreakpointDelegate(BreakpointEvent e) {
        super(e.thread());
        vars = new ThreadVariables(e.thread());
    }

    public Object propertyMissing(String name) {
        return vars.getProperty(name);
    }
}
