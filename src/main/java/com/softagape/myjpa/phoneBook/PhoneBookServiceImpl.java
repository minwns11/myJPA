package com.softagape.myjpa.phoneBook;

import com.softagape.myjpa.catCategory.CategoryEntity;
import com.softagape.myjpa.catCategory.ICategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import  java.util.List;
import java.util.Optional;

@Service
public class PhoneBookServiceImpl implements IPhoneBookService<IPhoneBook> {
    @Autowired
    private PhoneBookJpaRepository phoneBookJpaRepository;

    private boolean isValidInsert(IPhoneBook dto) {
        if (dto == null) {
            return false;
        } else if (dto.getName() == null || dto.getName().isEmpty() ) {
            return false;
        } else if (dto.getCategory() == null ) {
            return false;
        }
        return true;
    }

    private List<IPhoneBook> getIPhoneBookList(List<PhonebookEntity> list) {
        if ( list == null || list.size() <= 0 ) {
            return new ArrayList<>();
        }
        List<IPhoneBook> result = new ArrayList<>();
        for( PhonebookEntity entity : list ) {
            result.add((IPhoneBook)entity);
        }
//        List<IPhoneBook> result = list.stream()
//                .map(item -> (IPhoneBook)item)
//                .toList()
        return result;
    }

    @Override
    public IPhoneBook findById(Long id) {
        Optional<PhonebookEntity> find = this.phoneBookJpaRepository.findById(id); // Optional final이므로 부모가 될 수 없음. <PhonebookEntity>에 저장할 데이터(Long, null) 부모 데이터 저장X, 자동 형변환, null값이 들어가도 강제종료를 막기 위한
        return find.orElse(null);
    }

    @Override
    public List<IPhoneBook> getAllList() {
        List<IPhoneBook> list = new ArrayList<>();
        for ( PhonebookEntity entity : this.phoneBookJpaRepository.findAll() ) { //  @Entity 선언만 가능, 부모 배열로 형변환
            list.add( (IPhoneBook)entity );
        }
        return list;
    }

    @Override
    public IPhoneBook insert(IPhoneBook phoneBook) throws Exception {
        if ( !this.isValidInsert(phoneBook)) {
            return null;
        }
        PhonebookEntity entity = new PhonebookEntity();
        entity.copyFields(phoneBook);
        entity.setId(0L);
        IPhoneBook result = this.phoneBookJpaRepository.saveAndFlush(entity); // save - flush 같이 표기,
        return result;
    }

    @Override
    public boolean remove(Long id) {
        IPhoneBook find = this.findById(id);
        if ( find != null ) {
            this.phoneBookJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private boolean setIphoneBookIsNotNull(IPhoneBook to, IPhoneBook from) {
        if ( to == null || from == null ) {
            return false;
        }
        if ( from.getName() != null && !from.getName().isEmpty() ) {
            to.setName(from.getName());
        }
        if ( from.getCategory() != null ) {
            to.setCategory(from.getCategory());
        }
        if ( from.getPhoneNumber() != null && !from.getPhoneNumber().isEmpty() ) {
            to.setPhoneNumber(from.getPhoneNumber());
        }
        if ( from.getEmail() != null && !from.getEmail().isEmpty() ) {
            to.setEmail(from.getEmail());
        }
        return true;
    }

    @Override
    public IPhoneBook update(Long id, IPhoneBook phoneBook) {
        IPhoneBook find = this.findById(id);
        if (find == null) {
            return null;
        }
//        PhonebookEntity entity = PhonebookEntity.builder()
//                .id(id).name(find.getName()).category(find.getCategory())
//                .phoneNumber(find.getPhoneNumber()).build();
//        entity.copyFields(phoneBook);
//        PhonebookEntity result = this.phoneBookJpaRpository.saveAndFlush(entity);
//        entity.setName("entity");
        find.copyFields(phoneBook);
        PhonebookEntity result = this.phoneBookJpaRepository.saveAndFlush((PhonebookEntity) find);
        return result;
    }

    @Override
    public List<IPhoneBook> getListFromName(String findName) {
        if (findName == null || findName.isEmpty()) {
            return new ArrayList<>();
        }
        List<IPhoneBook> result = this.getIPhoneBookList(this.phoneBookJpaRepository.findAllByNameContains(findName));
//        List<PhonebookEntity> list = this.phoneBookJpaRpository.findAllByNameContains(findName);
//        List<IPhoneBook> result = new ArrayList<>();
//        for ( PhonebookEntity item : list ) {
//            result.add((IPhoneBook) item);
//        }
        return result;
    }

    @Override
    public List<IPhoneBook> getListFromCategory(ICategory category) {
        if (category == null) {
            return new ArrayList<>();
        }
        List<IPhoneBook> result = this.getIPhoneBookList(this.phoneBookJpaRepository.findAllByCategory((CategoryEntity) category));
//        List<PhonebookEntity> list = this.phoneBookJpaRpository.findAllByCategory(category);
//        List<IPhoneBook> result = list.stream()
//                .map(x -> (IPhoneBook)x)
//                .toList();
        return result;
    }

    @Override
    public List<IPhoneBook> getListFromPhoneNumber(String findPhone) {
        if (findPhone == null || findPhone.isEmpty()) {
            return new ArrayList<>();
        }
        List<IPhoneBook> result = this.getIPhoneBookList(this.phoneBookJpaRepository.findAllByPhoneNumberContains(findPhone));
//        List<PhonebookEntity> list = this.phoneBookJpaRpository.findAllByPhoneNumberContains(findPhone);
//        List<IPhoneBook> result = list.stream()
//                .map(item -> (IPhoneBook)item)
//                .toList();
        return result;
    }

    @Override
    public List<IPhoneBook> getListFromEmail(String findEmail) {
        if (findEmail == null || findEmail.isEmpty()) {
            return new ArrayList<>();
        }
        List<IPhoneBook> result = this.getIPhoneBookList(this.phoneBookJpaRepository.findAllByEmailContains(findEmail));
//        List<PhonebookEntity> list = this.phoneBookJpaRpository.findAllByEmailContains(findEmail);
//        List<IPhoneBook> result = list.stream()
//                .map(node -> (IPhoneBook)node)
//                .toList();
        return result;
    }

}

