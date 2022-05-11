package com.fundamentos.springboot.fundamentos.repository;

import com.fundamentos.springboot.fundamentos.dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositiry extends JpaRepository<User,Long> {

   @Query("Select u from User u where u.email = ?1 ")
    Optional<User> findByUserEmail(String email);

   @Query("select  u from User u where u.name like ?1%")
   List<User> findAndSort(String name, Sort sort);

   //Query methods
   //Query methods
    List<User> findByname(String name);

    Optional<User> findByEmailAndName(String email,String name);

    List<User> findByNameLike(String name);
    List<User> findByNameOrEmail(String name,String email);

    List<User> findByBirthDateBetween(LocalDate begin, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    //Uso de JPQL con named parameters
    @Query("select new com.fundamentos.springboot.fundamentos.dto.UserDto(u.id,u.name,u.birthDate)" +
            "from User u " +
            "where u.birthDate=:parametroFecha and u.email=:parametroEmail")

    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date,
                                                @Param("parametroEmail") String email);

   // List<User> findAll();
}
