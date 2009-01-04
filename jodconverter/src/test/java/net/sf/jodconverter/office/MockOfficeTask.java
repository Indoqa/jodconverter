//
// JODConverter - Java OpenDocument Converter
// Copyright (C) 2004-2008 - Mirko Nasato <mirko@artofsolving.com>
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, you can find it online
// at http://www.gnu.org/licenses/lgpl-2.1.html.
//
package net.sf.jodconverter.office;

import static net.sf.jodconverter.office.OfficeUtils.*;

import com.sun.star.beans.PropertyValue;
import com.sun.star.frame.XComponentLoader;
import com.sun.star.lang.XComponent;
import com.sun.star.util.XCloseable;

public class MockOfficeTask implements OfficeTask {

    private long delayTime = 0L;

    private boolean completed = false;

    public MockOfficeTask() {
        // default
    }

    public MockOfficeTask(long delayTime) {
        this.delayTime = delayTime;
    }

    public void execute(OfficeContext context) throws OfficeException {
        XComponentLoader loader = cast(XComponentLoader.class, context.getService(SERVICE_DESKTOP));
        assert loader != null : "desktop object is null";
        try {
            PropertyValue[] arguments = new PropertyValue[] { property("Hidden", true) };
            XComponent document = loader.loadComponentFromURL("private:factory/swriter", "_blank", 0, arguments);
            if (delayTime > 0) {
                Thread.sleep(delayTime);
            }
            cast(XCloseable.class, document).close(true);
            completed = true;
        } catch (Exception exception) {
            throw new OfficeException("failed to create document", exception);
        }
    }

    public boolean isCompleted() {
        return completed;
    }

}