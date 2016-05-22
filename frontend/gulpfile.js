'use strict';

var gulp = require('gulp');
var plugins = require('gulp-load-plugins')();
var runSequence = require('run-sequence');

var src = './src/main';
var build = './build/public';

gulp.task('index', function () {
    var target = gulp.src(src + '/index.html');
    var sources = gulp.src([build + '/**/*.js', build + '/**/*.css']);
    var injectOptions = {
        addRootSlash: false,
        ignorePath: 'build/public'
    };
    return target.pipe(plugins.inject(sources, injectOptions)).pipe(gulp.dest(build));
});

gulp.task('js', function () {
    var sources = gulp.src(src + '/**/*.js');
    return sources.pipe(gulp.dest(build));
});

gulp.task('css', function () {
    var sources = gulp.src(src + '/**/*.css');
    return sources.pipe(gulp.dest(build));
});

gulp.task('clean', function () {
    return gulp.src(build, {read: false}).pipe(plugins.clean());
});

gulp.task('default', ['clean'], function () {
    runSequence(['css', 'js'], 'index');
});
