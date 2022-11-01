package edu.elpeanuto.tms.tags;

import edu.elpeanuto.tms.model.Product;
import edu.elpeanuto.tms.model.enums.ProductType;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Simple custom JSTL tag which prints product values
 */
public class ProductTag extends TagSupport {
    private Product product;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            if(product.getType().equals(ProductType.Hot.name()))
                out.print(String.format("<td class=\"hot\">%s</td>", product.getName()));
            else
                out.print(String.format("<td>%s</td>", product.getName()));
            out.print(String.format("<td>%s</td>", product.getCountry()));
            out.print(String.format("<td>%s</td>", product.getCity()));
            out.print(String.format("<td>%s</td>", product.getPrice()));
            out.print(String.format("<td>%s</td>", product.getNumberOfTourists()));
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}