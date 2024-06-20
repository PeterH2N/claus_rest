package claus.rest.controllers;

import claus.backend.database.DB;
import claus.backend.domain.elements.CategoryDAO;
import claus.backend.domain.elements.ElementDAO;
import claus.backend.domain.elements.ElementRequirementDao;
import claus.backend.elements.Category;
import claus.backend.elements.Element;
import claus.backend.elements.ElementRequirement;
import claus.backend.elements.Tree;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ElementController
{
    @GetMapping("api/{cop}/element/{code}")
    Element getOne(@PathVariable String cop, @PathVariable String code) throws SQLException
    {
        var con = DB.getConnection();
        var elem = ElementDAO.get(con, cop, code);
        DB.freeConnection(con);
        return elem;
    }

    @GetMapping("api/{cop}/elements")
    List<Element> getAll(@PathVariable String cop) throws SQLException
    {
        var con = DB.getConnection();
        var elems = ElementDAO.getAllByCoP(con, cop);
        DB.freeConnection(con);
        return elems;
    }

    @GetMapping("api/{cop}/categories/parents")
    List<Category> getParents(@PathVariable String cop) throws SQLException
    {
        var con = DB.getConnection();
        var cats = CategoryDAO.getChildren(con, cop, "");
        DB.freeConnection(con);
        return cats;
    }

    @GetMapping("api/{cop}/categories/{parent}")
    List<Category> getChildren(@PathVariable String cop, @PathVariable String parent) throws SQLException
    {
        var con = DB.getConnection();
        var cats = CategoryDAO.getChildren(con, cop, parent);
        DB.freeConnection(con);
        return cats;
    }

    @GetMapping("api/{cop}/elements/{catCode}")
    List<Element> getByCategory(@PathVariable String cop, @PathVariable String catCode) throws SQLException
    {
        var con = DB.getConnection();
        var elems = ElementDAO.getBySpecificCategory(con, cop, catCode);
        DB.freeConnection(con);
        return elems;
    }

    @GetMapping("api/{cop}/requirements")
    List<ElementRequirement> getReqs(@PathVariable String cop) throws SQLException
    {
        var con = DB.getConnection();
        var reqs = ElementRequirementDao.getByCoP(con, cop);
        DB.freeConnection(con);
        return reqs;
    }

}
