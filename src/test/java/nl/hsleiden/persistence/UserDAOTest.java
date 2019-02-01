package nl.hsleiden.persistence;

import nl.hsleiden.model.User;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Peter van Vliet
 */
public class UserDAOTest
{
    private UserDAO subject;
    
    @Before
    public void setUp()
    {

        
        subject = new UserDAO();
    }
    
    @After
    public void tearDown()
    {

        
        subject = null;
    }
    
    @Test
    public void testGetSuccess()
    {

        
        String expectedName = "Second user";
        User actual = subject.get("a");
        
        assertNotNull(actual);
        assertEquals(expectedName, actual.getName());
    }
    
    @Test
    public void testGetFailed()
    {

        
        User actual = subject.get("b");
        
        assertNull(actual);
    }
    
    @Test
    public void testGetByEmailAddressSuccess()
    {

        
        String expectedName = "First user";
        User actual = subject.getByEmailAddress("first@user.com");
        
        assertNotNull(actual);
        assertEquals(expectedName, actual.getName());
    }
    
    @Test
    public void testGetByEmailAddressFailed()
    {

        
        User actual = subject.getByEmailAddress("third@user.com");
        
        assertNull(actual);
    }
    
    @Test
    public void testAdd()
    {

        
        User user = new User();
        user.setFirstname("Peter van Vliet");
        
//        subject.add(user);
        
        int expectedSize = 3;
        int actualSize = subject.getAll().size();
        
        assertEquals(expectedSize, actualSize);
    }
    
    @Test
    public void testUpdate()
    {
        User expected = new User();
        expected.setFirstname("Peter van Vliet");
        
        subject.update(expected);
        
        User actual = subject.get("a");
        
        assertSame(expected, actual);
    }
    
    @Test
    public void testDelete()
    {
        subject.delete("test@gmail.ocm");
        
        User actual = subject.get("test@gmail.com");
        
        assertNull(actual);
    }
}
