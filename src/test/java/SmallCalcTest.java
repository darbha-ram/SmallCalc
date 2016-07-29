import java.io.ByteArrayInputStream;
import org.junit.Test;
import static org.junit.Assert.*;
import com.ramdarbha.examples.SmallCalc;

public class SmallCalcTest 
{
    
    @Test
    public void testAdd()
    {
        String input;
        
        input = "add(1,2)\n";
        SmallCalc c1 = new SmallCalc(new ByteArrayInputStream(input.getBytes()));
        try {
            Integer t1 = c1.TerminatedExpre();
            assert(3 == t1);
        } catch (Exception e) {
            fail("parse failure");
        }
    }

    @Test
    public void testAddNeg()
    {
        String input;
        
        input = "add(1,-2)\n";
        SmallCalc c2 = new SmallCalc(new ByteArrayInputStream(input.getBytes()));
        try {
            Integer t2 = c2.TerminatedExpre();
            assert(-1 == t2);
        } catch (Exception e) {
            fail("parse failure");
        }
    }
        
    @Test
    public void testAddLarge()
    {
        String input;
        
        input = "add(1,22222222222222)\n";
        SmallCalc c3 = new SmallCalc(new ByteArrayInputStream(input.getBytes()));
        try {
             Integer t3 = c3.TerminatedExpre();
             assert(null == t3);
        } catch (Exception e) {
            fail("parse failure");
        }
    }

    @Test
    public void testAddAdd()
    {
        String input;
        
        input = "add(1,add(2,3))\n";
        SmallCalc c4 = new SmallCalc(new ByteArrayInputStream(input.getBytes()));
        try {
            Integer t4 = c4.TerminatedExpre();
            assert(6 == t4);
        } catch (Exception e) {
            fail("parse failure");
        }
    }

    @Test
    public void testMult()
    {
        String input;
        
        input = "mult(2,3)\n";
        SmallCalc c1 = new SmallCalc(new ByteArrayInputStream(input.getBytes()));
        try {
             Integer t1 = c1.TerminatedExpre();
            assert(6 == t1);
        } catch (Exception e) {
            fail("parse failure");
        }
    }

    @Test
    public void testMultAdd()
    {
        String input;
        
        input = "mult(-2,add(4,5))\n";
        SmallCalc c2 = new SmallCalc(new ByteArrayInputStream(input.getBytes()));
        try {
             Integer t2 = c2.TerminatedExpre();
             assert(-18 == t2);
        } catch (Exception e) {
            fail("parse failure");
        }
    }
        
    @Test
    public void testMultThree()
    {
        String input;
        
        input = "mult(4, mult(mult(3,2), 4))\n";
        SmallCalc c3 = new SmallCalc(new ByteArrayInputStream(input.getBytes()));
        try {
            Integer t3 = c3.TerminatedExpre();
            assert(96 == t3);
        } catch (Exception e) {
            fail("parse failure");
        }
    }
    
    @Test
    public void testLetAdd()
    {
        String input;
        
        input = "let(a, 4, add(a,10))\n";
        SmallCalc c1 = new SmallCalc(new ByteArrayInputStream(input.getBytes()));
        try {
            Integer t1 = c1.TerminatedExpre();
            assert(14 == t1);
        } catch (Exception e) {
            fail("parse failure");
        }
    }

    @Test
    public void testLetLetAdd()
    {
        String input;

        input = "let(a, 6, let(b, 10, add(a,b)))\n";
        SmallCalc c2 = new SmallCalc(new ByteArrayInputStream(input.getBytes()));
        try {
            Integer t2 = c2.TerminatedExpre();
            assert(16 == t2);
        } catch (Exception e) {
            fail("parse failure");
        }
    }

    @Test
    public void testLetBadVar()
    {
        String input;

        input = "let(a, 6, let(b, 10, add(a,c)))\n";
        SmallCalc c3 = new SmallCalc(new ByteArrayInputStream(input.getBytes()));
        try {
            Integer t3 = c3.TerminatedExpre();
            assert(null == t3);
        } catch (Exception e) {
            fail("parse failure");
        }
    }

    @Test
    public void testMissingBrace()
    {
        String input;

        input = "let(a, 6, add(a, 10)\n";
        SmallCalc c3 = new SmallCalc(new ByteArrayInputStream(input.getBytes()));
        try {
            Integer t3 = c3.TerminatedExpre();
            fail("Missing brace should have failed parse");
        } catch (Exception e) {
        }
    }

    @Test
    public void testExcessBrace()
    {
        String input;

        input = "let(a, 6, add(a, 10)))\n";
        SmallCalc c3 = new SmallCalc(new ByteArrayInputStream(input.getBytes()));
        try {
            Integer t3 = c3.TerminatedExpre();
            fail("Excess brace should have failed parse");
        } catch (Exception e) {
        }
    }



}

