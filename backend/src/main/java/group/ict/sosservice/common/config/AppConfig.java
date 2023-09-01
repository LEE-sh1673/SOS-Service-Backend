package group.ict.sosservice.common.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import group.ict.sosservice.user.model.Role;
import group.ict.sosservice.user.model.User;
import group.ict.sosservice.user.service.dto.SignUpRequestDto;
import group.ict.sosservice.user.service.dto.UserResponseDto;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper
            .getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
            .setFieldMatchingEnabled(true);

        modelMapper.createTypeMap(User.class, UserResponseDto.class)
            .addMapping(source -> source.getEmail().getValue(), UserResponseDto::setEmail);

        //TODO: change later
        modelMapper.createTypeMap(SignUpRequestDto.class, User.class)
            .addMapping(SignUpRequestDto::getEmail, User::updateEmail)
            .addMapping(source -> Role.USER, User::updateRole);

        return modelMapper;
    }
}
