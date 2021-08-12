package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.Testimonial;



public interface TestimonialRepository extends JpaRepository<Testimonial, Long>  {
  
}
