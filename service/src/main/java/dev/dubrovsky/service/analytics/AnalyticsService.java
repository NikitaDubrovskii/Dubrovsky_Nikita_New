package dev.dubrovsky.service.analytics;

import dev.dubrovsky.dto.request.analytics.NewAnalyticsRequest;
import dev.dubrovsky.dto.request.analytics.UpdateAnalyticsRequest;
import dev.dubrovsky.dto.response.analytics.AnalyticsResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.analytics.Analytics;
import dev.dubrovsky.repository.analytics.AnalyticsRepository;
import dev.dubrovsky.repository.user.UserRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AnalyticsService implements IAnalyticsService {

    private final AnalyticsRepository analyticsRepository;
    private final UserRepository userRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public void create(NewAnalyticsRequest request) {
        ValidationUtil.checkEntityPresent(request.getUserId(), userRepository);

        Analytics analytics = mapper.map(request, Analytics.class);
        analytics.setUser(userRepository
                .findById(request.getUserId())
                .orElseThrow(DbResponseErrorException::new));
        analytics.setTimestamp(LocalDateTime.now());

        analyticsRepository.save(analytics);
    }

    @Override
    public AnalyticsResponse getById(Integer id) {
        ValidationUtil.checkId(id, analyticsRepository);

        Analytics analytics = analyticsRepository.findById(id).orElseThrow(DbResponseErrorException::new);
        return analytics.mapToResponse();
    }

    @Override
    public List<AnalyticsResponse> getAll() {
        if (analyticsRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<AnalyticsResponse> responses = new ArrayList<>();
            List<Analytics> all = analyticsRepository.findAll();

            all.forEach(analytics -> responses.add(analytics.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateAnalyticsRequest request, Integer id) {
        ValidationUtil.checkId(id, analyticsRepository);

        Analytics analytics = analyticsRepository.findById(id).orElseThrow(DbResponseErrorException::new);

        if (request.getActivity() != null && !request.getActivity().isEmpty()) {
            analytics.setActivity(request.getActivity());
        }
        if (request.getUserId() != null && request.getUserId() != 0) {
            ValidationUtil.checkEntityPresent(request.getUserId(), userRepository);
            analytics.setUser(userRepository.findById(request.getUserId()).orElseThrow(DbResponseErrorException::new));
        }
        analytics.setId(id);

        analyticsRepository.save(analytics);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, analyticsRepository);
        analyticsRepository.deleteById(id);
    }

}
