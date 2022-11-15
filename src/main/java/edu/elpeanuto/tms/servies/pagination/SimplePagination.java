package edu.elpeanuto.tms.servies.pagination;

import edu.elpeanuto.tms.model.Entity;
import edu.elpeanuto.tms.servies.exception.DAOException;
import edu.elpeanuto.tms.servies.exception.NoEntityException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Stream;

public class SimplePagination {
    /**
     * Get data by pieces from db
     * @param dao DAO that implements Pagination interface {@link Pagination check}
     * @param request Http servlet request.
     * @param numOfStrings Number of string on one page.
     * @return List of entities from db.
     * @throws DAOException Exception: {@link edu.elpeanuto.tms.servies.exception.DAOException check}
     * @throws NoEntityException Exception: {@link edu.elpeanuto.tms.servies.exception.NoEntityException check}
     */
    public static List<? extends Entity<?>> pagination(Pagination<?> dao, HttpServletRequest request, Integer numOfStrings) throws DAOException, NoEntityException {
        int page = Integer.parseInt(request.getParameter("page"));

        request.setAttribute("positionList", pages(dao, numOfStrings));

        return dao.getPagination(page * numOfStrings - numOfStrings, numOfStrings);
    }

    private static List<Integer> pages(Pagination<?> dao, Integer numOfStrings) throws DAOException, NoEntityException {
        int pagesLimit = (int) Math.ceil(dao.getNumberOfNotes().orElseThrow(NoEntityException::new) / (double) numOfStrings);
        List<Integer> list = Stream.iterate(1, n -> n + 1).limit(pagesLimit).toList();

        return list.size() != 1 ? list : null;
    }
}
