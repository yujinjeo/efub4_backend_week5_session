package efub.session.blog.account.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressVO {
    private String state;
    private String city;
    private String zipcode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressVO addressVO = (AddressVO) o;
        return Objects.equals(state, addressVO.state) &&
                Objects.equals(city, addressVO.city) &&
                Objects.equals(zipcode, addressVO.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, city, zipcode);
    }
}
