package com.prismanet

import static org.junit.Assert.*
import org.junit.*

class UserIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }


    @Test
    void testFirstSaveEver() {
        def user = new User(userId: 'oscar', password: 'fiuba')
        assertNotNull user.save()
        assertNotNull user.id
        def foundUser = User.get(user.id)
        assertEquals 'oscar', foundUser.userId
    }

}
