package com.doug.jfx.store.repositories;

import com.doug.jfx.store.models.Picture;
import com.doug.jfx.store.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
