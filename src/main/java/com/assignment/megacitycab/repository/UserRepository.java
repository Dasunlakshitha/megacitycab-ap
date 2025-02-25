package com.assignment.megacitycab.repository;

import com.assignment.megacitycab.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // User findByUsername(String username);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (username, password,role, token, code) VALUES" +
            "(:username, :password,:user_role, :token, :code)", nativeQuery = true)
    int registerUser(@Param("username") String user_name,
                     @Param("password") String password,
                     @Param("user_role") String user_role,
                     @Param("token") String token,
                     @Param("code") int code);


    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET token = null, code= 0, verified = 1, verified_at = NOW() " +
            "WHERE token = :token AND code=:code", nativeQuery = true)
    int verifyAccount(@Param("token") String token, @Param("code") int code);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET token= :token, code= :code, password = :password WHERE email = :email", nativeQuery = true)
    int forgotPassword(@Param("token") String token,
                       @Param("code") String Code,
                       @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET token = null, code = 0, password = :password, updated_at = Now()" +
            "WHERE token= :token AND code = :code", nativeQuery = true)
    int changePassword(@Param("token") String token,
                       @Param("code") String code,
                       @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password = :password, updated_at = Now() WHERE email = :email", nativeQuery = true)
    int updatePasswordByEmail(@Param("password") String password, @Param("email") String email);

    @Query(value = "SELECT token, code FROM users WHERE token= :token AND code= :code", nativeQuery = true)
    List<String> checkTokenAndCode(@Param("token") String token, @Param("code") int code);

    @Query(value = "SELECT username FROM users WHERE username= :username", nativeQuery = true)
    String doesUsernameExist(@Param("username") String username);

    @Query(value = "SELECT verified FROM users WHERE username = :username", nativeQuery = true)
    int isAccountVerified(@Param("username") String username);

    @Query(value = "SELECT password FROM users WHERE username = :username", nativeQuery = true)
    String getDbPasswordByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    User getUserDetailsByUsername(@Param("username") String username);

    @Query(value = "SELECT password FROM users WHERE token= :token AND code = :code", nativeQuery = true)
    String getHashedPasswordByTokenAndCode(@Param("token") String token, @Param("code") String code);


}// END OF REPOSITORY INTERFACE.
