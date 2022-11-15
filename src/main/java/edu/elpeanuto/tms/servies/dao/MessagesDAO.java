package edu.elpeanuto.tms.servies.dao;

import edu.elpeanuto.tms.model.Message;
import edu.elpeanuto.tms.servies.dto.MessageDTO;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.pagination.Pagination;

import java.util.List;
import java.util.Optional;

/**
 * The interface that is needed to expand the BaseDAO if necessary
 * @see edu.elpeanuto.tms.servies.dao.BaseDAO
 */
public interface MessagesDAO extends BaseDAO<Long, Message>, Pagination<Message> {
    boolean adminUpdate(MessageDTO messageDTO) throws DAOException;

    List<Message> getPaginationByUserId(Integer start, Integer numOfStrings, Long userId) throws DAOException;

    Optional<Integer> getNumberOfNotesByUserId(Long userId) throws DAOException;

    List<Message> getPaginationNotAnswered(Integer start, Integer numOfStrings) throws DAOException;

    Optional<Integer> getNumberOfNotesNotAnswered() throws DAOException;
}
