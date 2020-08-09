package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.AirplaneType;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.AssessedValue;
import com.mcb.creditfactory.repository.AirplaneRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AirplaneServiceImpl implements AirplaneService {

    private final ExternalApproveService approveService;
    private final AirplaneRepository airplaneRepository;
    private final AirplaneType type;

    @Override
    public boolean approve(AirplaneDto dto) {
        return approveService.approve(new AirplaneAdapter(dto), type) == 0;
    }

    @Override
    public Airplane save(Airplane plain) {
        return airplaneRepository.save(plain);
    }

    @Override
    public Optional<Airplane> load(Long id) {
        return airplaneRepository.findById(id);
    }

    @Override
    public Airplane fromDto(AirplaneDto dto) {
        return new Airplane(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getYear(),
                dto.getManufacturer(),
                dto.getFuelCapacity(),
                dto.getSeats()
        );
    }

    @Override
    public AirplaneDto toDTO(Airplane airplane) {
        AssessedValue<Airplane> assessedValue =
                (AssessedValue<Airplane>) airplane.getSetValues()
                        .stream()
                        .max(Comparator.comparing(AssessedValue::getDateValue))
                        .get();

        return new AirplaneDto(
                airplane.getId(),
                airplane.getBrand(),
                airplane.getModel(),
                airplane.getYearOfIssue(),
                airplane.getManufacturer(),
                airplane.getFuelCapacity(),
                airplane.getSeats(),
                assessedValue.getValue(),
                assessedValue.getDateValue()

        );
    }

    @Override
    public Long getId(Airplane airplane) {
        return airplane.getId();
    }
}
