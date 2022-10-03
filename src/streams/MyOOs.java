package streams;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyOOs extends ObjectOutputStream {
    public MyOOs(OutputStream out) throws IOException {
        super(out);
    }

    protected MyOOs() throws IOException, SecurityException {
    }
}
