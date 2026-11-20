#ifdef RCT_NEW_ARCH_ENABLED

#import <React/RCTViewComponentView.h>
#import <UIKit/UIKit.h>
 

NS_ASSUME_NONNULL_BEGIN

@interface RNDatePicker : RCTViewComponentView

- (void)setDate:(NSDate *)date;
- (void)setMinimumDate:(NSDate *)date;
- (void)setMaximumDate:(NSDate *)date;

@end

NS_ASSUME_NONNULL_END

#else
#import "DatePicker.h"
#import <UIKit/UIKit.h>

@interface RNDatePicker : DatePicker


@end

#endif

