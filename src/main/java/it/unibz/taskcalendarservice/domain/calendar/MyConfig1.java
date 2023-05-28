package it.unibz.taskcalendarservice.domain.calendar;

import it.unibz.taskcalendarservice.application.calendar.CalendarEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MyConfig1 {
    @Bean
    public CalendarEventRepository CERepository() {
        CalendarEventRepository CERepo = new CalendarEventRepository() {
            @Override
            public List<CalendarEvent> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<CalendarEvent> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends CalendarEvent> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends CalendarEvent> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<CalendarEvent> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public CalendarEvent getOne(Long aLong) {
                return null;
            }

            @Override
            public CalendarEvent getById(Long aLong) {
                return null;
            }

            @Override
            public CalendarEvent getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends CalendarEvent> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends CalendarEvent> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends CalendarEvent> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public <S extends CalendarEvent> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends CalendarEvent> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends CalendarEvent> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends CalendarEvent, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }

            @Override
            public <S extends CalendarEvent> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public List<CalendarEvent> findAll() {
                return null;
            }

            @Override
            public List<CalendarEvent> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
         public Optional<CalendarEvent> findById(Long id) {
               return Optional.empty();
          }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public CalendarEvent save(CalendarEvent calendarEvent) {
                return null;
            }

            @Override
            public void deleteById(Long id) {

            }

            @Override
            public void delete(CalendarEvent entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends CalendarEvent> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public Optional<CalendarEventService> findByName(String name) {
                return Optional.empty();
            }
        };
        return CERepo;
    }
}



